package com.example.roadside.data.models;

public class Payment {

    public enum Method { MOMO, VNPAY, CARD, CASH }

    private String id;
    private String requestId;
    private double baseFee;
    private double partsFee;
    private double surcharge;
    private double discount;
    private double totalAmount;
    private Method method;
    private boolean confirmed;
    private String invoiceEmail;

    public Payment() { }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public double getBaseFee() { return baseFee; }
    public void setBaseFee(double baseFee) { this.baseFee = baseFee; }

    public double getPartsFee() { return partsFee; }
    public void setPartsFee(double partsFee) { this.partsFee = partsFee; }

    public double getSurcharge() { return surcharge; }
    public void setSurcharge(double surcharge) { this.surcharge = surcharge; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Method getMethod() { return method; }
    public void setMethod(Method method) { this.method = method; }

    public boolean isConfirmed() { return confirmed; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    public String getInvoiceEmail() { return invoiceEmail; }
    public void setInvoiceEmail(String invoiceEmail) { this.invoiceEmail = invoiceEmail; }
}
