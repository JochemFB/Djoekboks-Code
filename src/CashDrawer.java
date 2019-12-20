public class CashDrawer
{
    public double revenue;

    public void emptyCashDrawer()
    {
        System.out.println("You collected €" + getRevenue());
        revenue = 0.00;
    }

    public double getRevenue()
    {
        return this.revenue;
    }
}
