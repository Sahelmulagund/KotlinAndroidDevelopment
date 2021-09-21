package com.icg.training.data_provider

import com.icg.training.model.InfoModel

object DataProvider {
    fun getAndroidDetails():ArrayList<InfoModel>{
        return arrayListOf(
            InfoModel("1.0","No name",
                             1, "supports web browser, camera, access web email server (POP3, IMAP4, and SMTP), Google Calendar, Google Maps...", "23-09-2008"),

            InfoModel("1.5","Cupcake",
                3, "It has Linux kernel 2.6.27. It supports third-party virtual keyboard, Video recording and playback in MPEG-4, Copy and paste feature, Animated screen translation...", "27-04-2009"),

            InfoModel("1.6","Donut",
                4, "voice and text entry search, bookmark history, contacts, web...", "15-09-2009"),

            InfoModel("2.0","Eclair",
                5, "expanded account sync, Microsoft Exchange email support, Bluetooth 2.1, ability to tap a Contact photo and select to call...", "26-10-2009"),



            InfoModel("2.2.x","Froyo",
                8, "Browser application, support Android Cloud to Device Messaging service, Adobe Flash support, security updates, and performance improvement...", "20-05-2010"),

            InfoModel("2.3","Gingerbread",
                9, "support for extra-large screen size and resolutions, updated user interface design with increased simplicity and speed...", "06-12-2010"),



            InfoModel("3.0","Honeycomb",
                11, "simplified multitasking tapping Recent Application in system Bar, redesign the keyboard making fast typing, quick access to camera exposure, hardware acceleration...", "22-02-2011"),

            InfoModel("4.0.1","Ice Cream Sandwich",
                14, "separation of widgets in a new tab, integrated screenshot capture, improved error correction on the keyboard...", "18-10-2011"),

            InfoModel("4.1.x","Jelly Bean",
                16, " smoother user interface, enhance accessibility, expandable notification, fixed bug on Nexus 7, one-finger gestures...", "09-07-2012"),

            InfoModel("4.4","KitKat",
                19, "clock no longer display bold hours, wireless printing capability, WebViews are based on Chromium engine...", "31-10-2013"),

            InfoModel("5.0","Lollipop",
                21, "Project Volta for battery life improvement, multiple user accounts, audio input, and output through USB devices, join Wi-Fi networks...", "12-11-2014"),

            InfoModel("6.0","Marshmallow",
                23, "App Standby feature, introduce the Doze mode to save battery life, native fingerprint reader support, run-time permission requests, USB-C support...", "05-10-2015"),

            InfoModel("7.0","Nougat",
                24, "picture-in-picture support, support manager APIs, circular app icons support, send GIFs directly from the default keyboard...", "22-08-2016"),

            InfoModel("8.0","Oreo",
                26, " Google Play Protect, Integrated printing support, Neural network API, shared memory API, Android Oreo Go Edition...", "21-08-2017"),

            InfoModel("9.0","Pie",
                28, "\"screenshot\" button has been added, battery percentage always shown on display...", "06-08-2018"),

            InfoModel("10","Android10",
                29, "Android 10 has developed under the codename \"Android Q\". It was initially announced by Google on March 13, 2019 and its first beta version was released on same day and its second beta was released on April 3, 2019...", "03-09-2019"),
            InfoModel("11","Android11",
                30, "The alphabetic naming system of Android, based on deserts, was stopped since Android 10. So therefore, this operating system has branded with \"Android 11\"...", "08-09-2020"),
        )

    }
}
