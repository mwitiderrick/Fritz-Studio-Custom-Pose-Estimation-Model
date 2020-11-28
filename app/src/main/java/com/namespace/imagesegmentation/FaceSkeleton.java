package com.namespace.imagesegmentation;

import ai.fritz.vision.poseestimation.Skeleton;

public class FaceSkeleton extends Skeleton {
    public static String OBJECT_NAME = "face";

    public static String[] FACE = {
            "left eye",
            "right eye",
            "nose"
    };


    public FaceSkeleton() {
        super(OBJECT_NAME, FACE);
    }
}
