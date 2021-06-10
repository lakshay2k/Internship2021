package com.lakshay.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException 
    {
    	Regions clientRegion = Regions.US_EAST_1;
        String bucketName = "raniai";
        //String stringObjKeyName = "kuch bhi";
        String fileObjKeyName = "newfile2.py";
        String fileName = "C:\\Users\\lakshay sharma\\Desktop\\sample2.py";

        AWSCredentials credentials = new BasicAWSCredentials("AKIAZYEKRKXOLB74EIV2","Vabi9jhOIrg/kTBFG9h6MgedmVknUADJvpcP0d3l");
        try {
            
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            		.withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(clientRegion)
                    .build();

          //  s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            System.out.println("File Inserted check the bucket");
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("title", "someTitle");
            request.setMetadata(metadata);
            s3Client.putObject(request);

            
        } catch (AmazonServiceException e) {
        
            e.printStackTrace();
        } catch (SdkClientException e) {
         
            e.printStackTrace();
        }
    }
}
