package com.namespace.imagesegmentation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import ai.fritz.core.Fritz;
import ai.fritz.vision.FritzVision;
import ai.fritz.vision.FritzVisionImage;
import ai.fritz.vision.imagesegmentation.FritzVisionSegmentationPredictorOptions;
import ai.fritz.vision.poseestimation.FritzVisionPosePredictor;
import ai.fritz.vision.poseestimation.FritzVisionPoseResult;
import ai.fritz.vision.poseestimation.Pose;
import ai.fritz.vision.poseestimation.PoseOnDeviceModel;

public class MainActivity extends AppCompatActivity {
    Button buttonClick;
    ImageView imageView;
    FritzVisionSegmentationPredictorOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fritz.configure(this, "YOUR_API_KEY");

        buttonClick = findViewById(R.id.buttonClick);
        imageView = findViewById(R.id.imageView);

    }
    public void detectKeyPoints(View view){
        PoseOnDeviceModel onDeviceModel = new PoseOnDeviceModel(
                "file:///android_asset/PoseFast1605860488.tflite",
                "12ed09a947714761a7afdb87f2f9838a",
                2,
                new FaceSkeleton(),
                8,
                false
        );

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.im);
        FritzVisionImage visionImage = FritzVisionImage.fromBitmap(image);
        FritzVisionPosePredictor predictor = FritzVision.PoseEstimation.getPredictor(onDeviceModel);
        FritzVisionPoseResult poseResult = predictor.predict(visionImage);
        List<Pose> poses = poseResult.getPoses();
        Log.i( "Info", "The poses are " +  poses.toString());
        Bitmap posesOnImage = visionImage.overlaySkeletons(poses);
        for (Pose pose : poses) {
            imageView.setImageBitmap(posesOnImage);
        }

    }
}

