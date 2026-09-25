package com.example.roadside.utils;

import com.example.roadside.data.models.Payment;

import java.text.NumberFormat;
import java.util.Locale;

/** Cost-breakdown math and VND currency formatting shared by RequestForm and Payment screens. */
public class PaymentHelper {

    private static final double VAT_RATE = 0.08;

    public static double calculateTotal(Payment payment) {
        double subtotal = payment.getBaseFee() + payment.getPartsFee() + payment.getSurcharge()
                - payment.getDiscount();
        return subtotal * (1 + VAT_RATE);
    }

    public static String formatVnd(double amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(Math.round(amount)) + "đ";
    }

    public static String methodDisplayName(Payment.Method method) {
        if (method == null) return "";
        switch (method) {
            case MOMO: return "Ví điện tử MoMo";
            case VNPAY: return "Cổng VNPay-QR";
            case CARD: return "Thẻ ATM / Visa / Mastercard";
            case CASH: return "Tiền mặt trực tiếp";
            default: return "";
        }
    }
}
