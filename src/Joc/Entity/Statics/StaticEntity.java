package Joc.Entity.Statics;

import Joc.Entity.Entity;
import Joc.Handler;

public abstract class StaticEntity extends Entity {


    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

    public void die(){};

    @Override
    public boolean isSolid(){
        return false;
    }
}
