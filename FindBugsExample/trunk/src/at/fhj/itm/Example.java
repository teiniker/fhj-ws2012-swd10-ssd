package at.fhj.itm;

public class Example
{

    public void toSomething(boolean b)
    {
        String s = null;
        if(b)
        {
            s = "Hallo";
        }
        s.length();
    }
    
    
    public void showNullPointer()
    {
        String s = null;
        
        @SuppressWarnings("unused")
		int l = s.length();
    }
}
