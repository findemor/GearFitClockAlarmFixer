# Gear Fit Clock Alarm Fixer
# for no samsung devices

This project tries to fix the issue when Gear Fit not vibrates if the Native Clock in not-samsung devices launch the alarm.

# Installation and usage

This is a Android Studio project just for testing (by now).

1. Install Gear Fit Manager (fixed for non samsung devices)

    [KitKat fixed version](https://github.com/findemor/GearFitClockAlarmFixer/raw/master/Dependences/GearFitManager.apk)

2. Install Gear Fit Clock Alarm Fixer (this app)

	[From Google Play](https://play.google.com/store/apps/details?id=com.devergence.gearfitclockalarmfixer)

3. Pair your Gear Fit normally via bluetooth
4. Enable Notifications in your Android settings

	> Settings > Security > Notification Access > Enable Gear Fit Manager

5. Open Gear Fit Manager app and enable this app notifications:

	> Gear Fit Manager > Notifications > General Notifications > Enable GearFit Cock Alarm Fixer check

6. In Gear Fit Manager disable Limit Notifications

	> Gear Fit Manager > Notifications > Disable Limit Notifications check
    
7. Your device is ready to show notifications in your Gear Fit when the native Clock Alarm is sounding.

	> You can see it working in this video [Youtuve demo](https://youtu.be/GETiMmu5y6E)
	
# Credits

by [@findemor](http://www.twitter.com/findemor) ( [blog.findemor.es](http://blog.findemor.es) )

Feel free to collaborate. 

## Compatibility

Tested (working perfectly) in:

* Nexus 4 - Android 4.4 Cyanogen

## Fixes

## v 1.0

Trying to fix this error in Nexus 5 by launching auxiliar standard notifications when the native clock launchs its broadcasts.
