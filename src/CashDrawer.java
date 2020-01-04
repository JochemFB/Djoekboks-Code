public class CashDrawer
{
    public double revenue;

    /**
     * Leeg de geldlade
     */
    public void emptyCashDrawer()
    {
        System.out.println("You collected €" + getRevenue());
        revenue = 0.00;
    }

    /**
     *
     * @return Het opgehaalde geld.
     */
    public double getRevenue()
    {
        return this.revenue;
    }
}
