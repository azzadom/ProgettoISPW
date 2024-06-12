package com.privateevents;

public class Main  {

    public static void main(String[] args) {
        String mainType = System.getenv("VIEW_TYPE");
        switch (MainType.valueOf(mainType)) {
            case FX:
                MainFX.run(args);
                break;
            case CLI:
                MainCLI.run();
                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }
}