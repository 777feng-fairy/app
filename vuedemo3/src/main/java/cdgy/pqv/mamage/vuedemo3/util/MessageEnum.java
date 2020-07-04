package cdgy.pqv.mamage.vuedemo3.util;

public enum MessageEnum {

    SUCC(200),
    ERROR(500);

    private final  int value;

    MessageEnum(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }


}
