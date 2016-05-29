package Converter;

/**
 *
 * @author Dragos-Alexandru
 */
public enum ByteSizes {
    B(0),Kb(1),Mb(2),Gb(3);
    
    public static long ATTACMENT_LIMIT = 2000000;
    double value;
    
    private ByteSizes(int value){
        this.value = Math.pow(1024, value);
    }
    
    public static String getSize(long byteSize){
        
        for(int i = ByteSizes.values().length - 1; i >= 0; i--){
            int convertedValue = (int)(byteSize / ByteSizes.values()[i].value);
            if(convertedValue > 0){
                return convertedValue + ByteSizes.values()[i].name();
            }
        }
        
        return null;
    }
}
