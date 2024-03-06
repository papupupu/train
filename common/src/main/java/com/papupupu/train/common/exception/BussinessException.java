package com.papupupu.train.common.exception;

public class BussinessException extends RuntimeException{
    private BussinessExceptionEnum E;
    public BussinessException(BussinessExceptionEnum e){
        this.E = e;
    }

    public BussinessExceptionEnum getE() {
        return E;
    }

    public void setE(BussinessExceptionEnum e) {
        E = e;
    }

    /**
     * 不输出堆栈信息，提高性能
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return  this;
    }
}
