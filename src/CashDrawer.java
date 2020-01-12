public class CashDrawer
{
    private double revenue;
    
    public void addMoneyToCashDrawer(double money)
    {
        this.revenue += money;
    }
    
    public void removeMoneyFromCashDrawer(double money)
    {
        this.revenue -= money;
    }

    /**
     * Leeg de geldlade
     */
    public void emptyCashDrawer()
    {
        System.out.println("You collected €" + getRevenue());
        revenue = 0.00;
    }
    
    public void printRevenue()
    {
        System.out.println("Total revenue: " + String.format("%.02f", this.revenue));
    }

    /**
     *
     * @return Het opgehaalde geld.
     */
    public double getRevenue()
    {
        return this.revenue;
    }
    
    /**
     * Set the revenue
     * @param revenue The revenue
     */
    public void setRevenue(double revenue)
    {
        this.revenue = revenue;
    }
    
}
