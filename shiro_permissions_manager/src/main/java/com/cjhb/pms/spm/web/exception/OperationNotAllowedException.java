package com.cjhb.pms.spm.web.exception;

/**
 * 操作不被允许异常
 * 
 * @author ArchX[archx@foxmail.com]
 */
public class OperationNotAllowedException extends WebAppException {

    private static final long serialVersionUID = 1L;

    public OperationNotAllowedException() {
        super();
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }

    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }

    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
