package app.utils.patterns;

import java.util.HashMap;

public abstract class Observer
{
    public abstract void update(HashMap<String, Object[]> stackContents);
    public abstract void alertParseError(String error);
}
