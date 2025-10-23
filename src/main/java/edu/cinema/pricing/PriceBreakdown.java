package edu.cinema.pricing;

public final class PriceBreakdown {
    private final double subtotal;
    private final double wednesdayDisc;
    private final double threeDSurcharge;
    private final double groupDisc;
    private final double total;

    // Constructeur PUBLIC, 5 doubles
    public PriceBreakdown(double subtotal, double wednesdayDisc, double threeDSurcharge, double groupDisc, double total) {
        this.subtotal = subtotal;
        this.wednesdayDisc = wednesdayDisc;
        this.threeDSurcharge = threeDSurcharge;
        this.groupDisc = groupDisc;
        this.total = total;
    }

    public double getSubtotal() { return subtotal; }
    public double getWednesdayDisc() { return wednesdayDisc; }
    public double getThreeDSurcharge() { return threeDSurcharge; }
    public double getGroupDisc() { return groupDisc; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format("Sous-total : %.2f, Remise mercredi : %.2f, 3D : %.2f, Groupe : %.2f, TOTAL : %.2f",
                subtotal, wednesdayDisc, threeDSurcharge, groupDisc, total);
    }
}

