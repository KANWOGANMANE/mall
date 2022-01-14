package com.sjq.commonutils;

public class ServiceResult
{
    public enum Code
    {
        OK(1), FAILED(0), UNAUTHORIZED(-1) , UNAuthentication(-2);
        private int code;

        Code(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }
    }

    private int status;
    private Code code;
    private String message;
    private Object data;
    private long duration = 0L;
    private String errorCode;

    private boolean success;

    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ServiceResult()
    {

    }

    public ServiceResult(Code code, String message, Object data)
    {
        this.status = code.getCode();
        this.setCode(code);
        this.message = message;
        this.data = data;
    }

    public ServiceResult(Code code, String message, Object data, long duration)
    {
        this.status = code.getCode();
        this.setCode(code);
        this.message = message;
        this.data = data;
        this.duration = duration;
    }

    public ServiceResult(Code code, String errorCode, String message, Object data, long duration)
    {
        this.status = code.getCode();
        this.setCode(code);
        this.message = message;
        this.data = data;
        this.duration = duration;
        this.errorCode = errorCode;
    }

    public ServiceResult(Code code, String errorCode, String message, Object data, long duration, boolean success) {
        this.status = code.getCode();
        this.setCode(code);
        this.message = message;
        this.data = data;
        this.duration = duration;
        this.errorCode = errorCode;
        this.success = success;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ServiceResult ok()
    {
        return ServiceResult.ok("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ServiceResult ok(Object data)
    {
        return ServiceResult.ok("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ServiceResult ok(String msg)
    {
        return ServiceResult.ok(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ServiceResult ok(String msg, Object data)
    {
        return new ServiceResult(Code.OK, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ServiceResult failed()
    {
        return ServiceResult.failed("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ServiceResult failed(String msg)
    {
        return ServiceResult.failed(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ServiceResult failed(String msg, Object data)
    {
        return new ServiceResult(Code.FAILED, msg, data);
    }

    /**
     * 鉴权失败
     */
    public static ServiceResult unauthorized(String msg){
        return new ServiceResult(Code.UNAUTHORIZED, msg,null);
    }
    /**
     * 没登录或认证信息过期
     */
    public static ServiceResult unauthentication(String msg){
        return new ServiceResult(Code.UNAuthentication, msg,null);
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getStatus()
    {
        return this.status;
    }

    public Code getCode()
    {
        return code;
    }

    public void setCode(Code code)
    {
        this.code = code;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

}
