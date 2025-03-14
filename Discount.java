public enum Discount {
    NATIONALMERIT(0.9), MILITARY(0.95), STUDENT(0.97), NONE(1);

    private final double discountRate;

    Discount(double discount) {
        this.discountRate = discount;
    }

    public double getDiscountRate() {
        return discountRate;
    }

}
