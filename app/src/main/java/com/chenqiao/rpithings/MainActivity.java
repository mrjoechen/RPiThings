package com.chenqiao.rpithings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    // GPIO Pin Name
    private static final String GPIO_NAME = "";
    private Gpio gpio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attempt to access the GPIO
        try {
            PeripheralManager manager = PeripheralManager.getInstance();
            gpio = manager.openGpio(GPIO_NAME);
        } catch (IOException e) {
            Log.w(TAG, "Unable to access GPIO", e);
        }



        Button btnExit = findViewById(R.id.btn_exit);



        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gpio != null) {
            try {
                gpio.close();
                gpio = null;
            } catch (IOException e) {
                Log.w(TAG, "Unable to close GPIO", e);
            }
        }
    }


    /**
     * To read a GPIO port as an input:
     *
     * Configure it as an input using setDirection() with the mode DIRECTION_IN.
     * Optional: Configure the pin voltage represented by an input value of true (active) with setActiveType():
     *
     * ACTIVE_HIGH: High voltage (near IOREF) is active. This is the default value.
     * ACTIVE_LOW: Low voltage (near zero) is active.
     * Access the current state with the getValue() method.
     * @param gpio
     * @throws IOException
     */
    public void configureInput(Gpio gpio) throws IOException {

        // Initialize the pin as an input
        gpio.setDirection(Gpio.DIRECTION_IN);
        // High voltage is considered active
        gpio.setActiveType(Gpio.ACTIVE_HIGH);


        // Read the active high pin state
        if (gpio.getValue()) {
            // Pin is HIGH
        } else {
            // Pin is LOW
        }

        // Register for all state changes
        gpio.setEdgeTriggerType(Gpio.EDGE_BOTH);
        gpio.registerGpioCallback(gpioCallback);

    }


    /**
     * Listening for input state changes
     * A GPIO port configured as an input can notify your app when its value changes. To register for these change events:
     *
     * Attach a GpioCallback to the active port connection.
     * Declare the state changes that trigger an interrupt event using the setEdgeTriggerType() method. The edge trigger supports the following four types:
     *
     * EDGE_NONE: No interrupt events. This is the default value.
     * EDGE_RISING: Interrupt on a value transition from false to true
     * EDGE_FALLING: Interrupt on a value transition from true to false
     * EDGE_BOTH: Interrupt on all transitions
     * Return true from within onGpioEdge() to indicate that the listener should continue receiving events for each port state change.
     */
    private GpioCallback gpioCallback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            // Read the active low pin state
            try {
                if (gpio.getValue()) {
                    // Pin is HIGH
                } else {
                    // Pin is LOW
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Continue listening for more interrupts
            return true;
        }

        @Override
        public void onGpioError(Gpio gpio, int error) {
            Log.w(TAG, gpio + ": Error event " + error);
        }
    };


    /**
     * To programmatically control the state of a GPIO port:
     *
     * Configure it as an output using setDirection() with appropriate initial value:
     *
     * DIRECTION_OUT_INITIALLY_HIGH: Set the initial output value to high voltage (near IOREF).
     * DIRECTION_OUT_INITIALLY_LOW: Set the initial output value to low voltage (near zero).
     * Optional: Configure the pin voltage represented by an output value of true (active) with setActiveType():
     *
     * ACTIVE_HIGH: High voltage (near IOREF) is active. This is the default value.
     * ACTIVE_LOW: Low voltage (near zero) is active.
     * Set the current state with the setValue() method.
     * @param gpio
     * @throws IOException
     */
    public void configureOutput(Gpio gpio) throws IOException {
        // Initialize the pin as a high output
        gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
        // Low voltage is considered active
        gpio.setActiveType(Gpio.ACTIVE_LOW);

        // Toggle the value to be LOW
        gpio.setValue(true);
    }
}
