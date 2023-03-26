// menu bar.xml

<!-- bottom_navigation_menu.xml -->
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/home"
        android:title="hello" />
    <item
        android:id="@+id/navigation_dashboard"
        android:icon="@drawable/ic_baseline_video_library_24"
        app:showAsAction="ifRoom"
        android:title="hello" />
    <item
        android:id="@+id/navigation_notifications"
        android:icon="@drawable/ic_baseline_settings_24"
        android:title="Hello" />

</menu>


//  main.xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:id="@+id/fragment_container"
    android:padding="10dp">
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:padding="0dp"
        android:layout_marginBottom="0dp"

        app:menu="@menu/menu_bar"/>
</FrameLayout>

// MainActivity.java



 BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // Replace the current fragment with the home fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new Fragment2())
                            .commit();
                    return true;

                case R.id.navigation_dashboard:
                    // Replace the current fragment with the dashboard fragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new BlankFragment())
                            .commit();
                    return true;

                case R.id.navigation_notifications:
                    // Replace the current fragment with the notifications fragment
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, new NotificationsFragment())
//                            .commit();
                    return true;
            }
            return false;
        });



// someextra thing to change ui in fragment

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        myTextView=view.findViewById(R.id.mytextview);
        myTextView.setText("Got it !");
        return view;
    }
