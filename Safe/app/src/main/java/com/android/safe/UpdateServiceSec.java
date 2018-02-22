/**
 * Ido Nimni 205883655
 * Daniel Kasman 206274946
 * Noy Shmaryahu 206082877
 * Segev Lahav 313381154
 * 89-211-05
 **/
package com.android.safe;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class creates a service for reload that will open in separate thread
 */
public class UpdateServiceSec extends IntentService implements GoogleApiClient.OnConnectionFailedListener, LocationListener,
        com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks
{
    public static final String DONE = "com.android.safe.Services.UpdateServiceSec.DONE";
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private static final long INTERVAL = 1000 * 10; // of getting information from google
    private static final long FASTEST_INTERVAL = 1000 * 5; // of getting information from google
    static Location loc = null;

    /**
     * the constructor of this class, use the parent constructor with
     * the name of the class
     */
    public UpdateServiceSec() {
        super(UpdateServiceSec.class.getName());
        // create request location from google
        createLocationRequest();
        // build google map client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    /**
     * this function is the constructor, that gets string and
     * call to the parent constructor with the string.
     * @param name the name of the service
     */
    public UpdateServiceSec(String name) {
        super(name);
    }

    /**
     * help function that create location request, and set the interval of the update.
     */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
    }

    /**
     * this function when needs to handle intent, sleep 1 sec
     * and then send broadcast that we finished.
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(DONE);
        this.sendBroadcast(i);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    /**
     * @param location
     * when the location change, use an animation to it, and set 4 markers
     * for friends, and one for us.
     */
    @Override
    public void onLocationChanged(final Location location) {
        loc = location;
        // new LatLng(loc.getLatitude(), loc.getLongitude());
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
                (com.google.android.gms.location.LocationListener) this);
    }
}