package at.ac.univie.se2.team0204.model;

public enum EFileType {
    PDF,
    PNG,
    TXT;

    private static String extractFileTypeFromPath(String filepath) {
        int separator = filepath.lastIndexOf(".");
        if(separator == -1) {
            return "";
        }
        return filepath.substring(separator+1).toUpperCase();
    }

    /**
     * Used to find out which type of file a specific filepath corresponds to.
     * @param filepath Path of the file where EFileType has to be extracted.
     * @return Corresponding EFileType object.
     */
    public static EFileType getEFileType(String filepath) {
        String fileType = extractFileTypeFromPath(filepath);
        if(!fileType.isEmpty()){
            for(EFileType type : EFileType.values()) {
                if(type.name().equals(fileType))
                    return type;
            }
        }
        return EFileType.TXT;
    }
}
