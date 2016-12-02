package com.meizu.push.sdk.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <T>
 * @author wangxinguo
 * @date 2016年7月15日
 * @time 下午8:02:20
 */
public final class ResultPack<T> implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int VOID = 0;
    public static final int FAILED = -1;
    public static final int SUCCESS = 1;
    private static final String ERROR_CODE = "500";
    private static final String SUCCESS_CODE = "200";

    private int _status = 0; // enum _value in -1 0 1
    private String _code;
    private String _comment = null;
    private T _value = null;
    private Throwable _error = null;
    private ErrorCode _errorCode = null;


    public ResultPack() {
    }

    private ResultPack(int status, T value, String code, String comment, Throwable error, ErrorCode errorCode) {
        _status = status;
        _value = value;
        _comment = comment;
        _code = code;
        _error = error;
        _errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return _errorCode;
    }

    /**
     * Get pack _state _value
     *
     * @return
     */
    public int status() {
        return _status;
    }

    public ResultPack<T> status(int status) {
        _status = status;
        return this;
    }

    public ResultPack<T> code(String code) {
        _code = code;
        return this;
    }

    /**
     * Get packed _message
     *
     * @return
     */
    public String comment() {
        return _comment;
    }

    /**
     * 设置说明
     *
     * @param comment 说明信息
     * @return 自身
     */
    public ResultPack<T> comment(String comment, Object... args) {
        this._comment = String.format(comment, args);
        return this;
    }

    /**
     * Get packed _value
     *
     * @return
     */
    public T value() {
        return _value;
    }

    public String code() {
        return _code;
    }

    public ResultPack<T> value(T value) {
        this._value = value;
        return this;
    }

    public Throwable error() {
        return _error;
    }

    public ResultPack<T> error(Throwable error) {
        this._error = error;
        if (error != null) {
            _status = ResultPack.FAILED;
            _comment = error.getMessage();
        }
        return this;
    }

    private ResultPack<T> _errorCode() {
        try {
            int code = Integer.parseInt(this._code);
            String comment = this.comment();
            this._errorCode = ErrorCode.valueOf(code, comment);
        } catch (NumberFormatException e) {
        }
        return this;
    }

    public boolean isFailed() {
        return _status == ResultPack.FAILED;
    }

    /**
     * 是否无结果
     *
     * @return
     */
    public boolean isVoid() {
        return _status == ResultPack.VOID;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSucceed() {
        return _status == ResultPack.SUCCESS;
    }

    /**
     * 重置Result为VOID状态。
     * <p>
     * 废物利用，环保。
     *
     * @return
     */
    public ResultPack<T> reset() {
        _status = VOID;
        _value = null;
        _comment = null;
        _error = null;
        return this;
    }

    /**
     * 有时在一个串行化过程中，我们可能需要保留comment作为附加输出信息，这时候可以用这个方法
     *
     * @param keepComment 是否同时清除comment。
     * @return
     */
    public ResultPack<T> reset(boolean keepComment) {
        _status = VOID;
        _value = null;
        _error = null;
        if (!keepComment) {
            _comment = null;
        }
        return this;
    }

    @Override
    public String toString() {
        return (new StringBuilder("RESULT:[").append(_status).append("] code:[").append(_code).
                append("] comment:[").append(_comment).append("] value:[").
                append(String.valueOf(_value))
                .append("] error:[").append(String.valueOf(_error)).append("]"))
                .append("] errorCode:[").append(_errorCode).append("]")
                .toString();
    }

    public Map<String, Object> toJson() {
        return toJson(null);
    }

    public Map<String, Object> toJson(Map<String, Object> jsons) {
        if (jsons == null) {
            jsons = new HashMap<String, Object>();
        }
        jsons.put("status", _status);
        jsons.put("comment", _comment);
        jsons.put("value", String.valueOf(_value));
        jsons.put("error", String.valueOf(_error));
        return jsons;
    }

    @Override
    public ResultPack<T> clone() {
        return new ResultPack<T>(_status, _value, _code, _comment, _error, _errorCode);
    }

    /**
     * 返回成功的Pack的简易辅助方法的
     *
     * @return ResultPack
     */
    public static <R> ResultPack<R> succeed() {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.SUCCESS).code(SUCCESS_CODE);
    }

    /**
     * 返回成功的Pack的简易辅助方法的
     *
     * @param value
     * @return ResultPack
     */
    public static <R> ResultPack<R> succeed(R value) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.SUCCESS).value(value).code(SUCCESS_CODE);
    }

    /**
     * 返回成功的Pack的简易辅助方法的
     *
     * @param comment
     * @param value
     * @return ResultPack
     */
    public static <R> ResultPack<R> succeed(String code, String comment, R value) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.SUCCESS).code(code).comment(comment).value(value);
    }

    public static <R> ResultPack<R> succeed(String comment) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.SUCCESS).comment(comment).code(SUCCESS_CODE);
    }

    /**
     * 得到失败的ResultPack的简易辅助方法的
     *
     * @return ResultPack
     */
    public static <R> ResultPack<R> failed() {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(ERROR_CODE).error(new Throwable())._errorCode();
    }

    /**
     * 得到失败的ResultPack的简易辅助方法
     *
     * @param comment
     * @return ResultPack
     */
    public static <R> ResultPack<R> failed(String comment) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(ERROR_CODE).error(new Throwable(comment))._errorCode();
    }

    /**
     * 得到失败的ResultPack的简易辅助方法
     *
     * @param code
     * @param comment
     * @param <R>
     * @return
     */
    public static <R> ResultPack<R> failed(String code, String comment) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(code).error(new Throwable(comment))._errorCode();
    }

    /**
     * 得到失败的ResultPack的简易辅助方法
     *
     * @param comment
     * @param e
     * @return
     */
    public static <R> ResultPack<R> failed(String comment, Throwable e) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(ERROR_CODE).comment(comment).error(e)._errorCode();
    }

    /**
     * 带失败value的辅助方法
     *
     * @param comment
     * @param value
     * @param <R>
     * @return
     */
    public static <R> ResultPack<R> failed(String comment, R value) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(ERROR_CODE).comment(comment).value(value)._errorCode();
    }

    /**
     * 得到失败的ResultPack的简易辅助方法
     *
     * @param e
     * @return ResultPacke
     */
    public static <R> ResultPack<R> failed(Throwable e) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.FAILED).code(ERROR_CODE).error(e)._errorCode();
    }

    /**
     * 返回无值结果的简易辅助方法的
     *
     * @return ResultPack
     */
    public static <R> ResultPack<R> none() {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.VOID);
    }

    /**
     * 返回无值结果的简易辅助方法的
     *
     * @param comment
     * @return ResultPack
     */
    public static <R> ResultPack<R> none(String comment) {
        ResultPack<R> result = new ResultPack<R>();
        return result.status(ResultPack.VOID).comment(comment);
    }

}
