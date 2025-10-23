package edu.cinema.pricing;

import java.time.DayOfWeek;
import java.util.List;

public class PricingEngine {

    // Prix de base selon le type de billet
    public double basePrice(TicketType type) {
        if (type == null) throw new IllegalArgumentException("Type de billet obligatoire");
        switch(type) {
            case ADULT:   return 10.00;
            case CHILD:   return 6.00;
            case SENIOR:  return 7.50;
            case STUDENT: return 8.00;
            default:      throw new IllegalArgumentException("Type inconnu");
        }
    }

    // Calcul du total (tickets, jour, 3D ou non)
    public PriceBreakdown computeTotal(List<TicketType> tickets, DayOfWeek day, boolean is3D) {
        if (tickets == null) throw new IllegalArgumentException("Tickets non renseignés");
        if (day == null) throw new IllegalArgumentException("Jour manquant");

        double subtotal = 0.0;
        for (TicketType t : tickets) {
            subtotal += basePrice(t);
        }

        // Remise mercredi : -20%
        double wednesdayDisc = 0.0;
        if (day == DayOfWeek.WEDNESDAY) {
            wednesdayDisc = -roundToCents(subtotal * 0.20);
        }

        // Supplément séance 3D : +2€/billet
        double threeDSurcharge = 0.0;
        if (is3D) {
            threeDSurcharge = roundToCents(2.0 * tickets.size());
        }

        // Remise groupe : -10% (si ≥ 4 billets)
        double groupDisc = 0.0;
        double totalBeforeGroup = subtotal + wednesdayDisc + threeDSurcharge;
        if (tickets.size() >= 4) {
            groupDisc = -roundToCents(totalBeforeGroup * 0.10);
        }

        // Total
        double total = totalBeforeGroup + groupDisc;
        total = roundToCents(total);

        return new PriceBreakdown(subtotal, wednesdayDisc, threeDSurcharge, groupDisc, total);
    }

    // Arrondi au centime
    public static double roundToCents(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

