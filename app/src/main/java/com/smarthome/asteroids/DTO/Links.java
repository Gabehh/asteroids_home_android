package com.smarthome.asteroids.DTO;

public class Links
{
    private String next;

    private String prev;

    private String self;

    public String getNext ()
    {
        return next;
    }

    public void setNext (String next)
    {
        this.next = next;
    }

    public String getPrev ()
    {
        return prev;
    }

    public void setPrev (String prev)
    {
        this.prev = prev;
    }

    public String getSelf ()
    {
        return self;
    }

    public void setSelf (String self)
    {
        this.self = self;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [next = "+next+", prev = "+prev+", self = "+self+"]";
    }
}
