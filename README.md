# RoundingSwitch
A simple toggle switch library   


# Design
All disign by myself



# Dependency   
- project build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- App build.gradle
```
implementation 'com.github.KKH0730:RoundingSwitch:1.0'
```



# Usage
- Use it in your layout xml.
You have to surround layout 
```
        <com.seno.arswitch.RoundingToggleSwitch
            android:id="@+id/roundingSwitch"
            android:layout_width="100dp"
            android:layout_height="50dp"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"/>
				
     ...
			
```



- and use it.
```

 binding?.roundingSwitch?.apply {
            duration = 100
            activeAnimation = true
            leftImageResource = R.drawable.ic_alarm_on
            rightImageResource = R.drawable.ic_alarm_off
            indicatorColor = getColor(R.color.teal_200)
            selectedTintColor = getColor(R.color.purple_500)
            unSelectedTintColor = getColor(R.color.purple_200)
            sideMargin = 10f
            onSwitchToggleListener = object: RoundingToggleSwitch.OnSwitchToggleListener {
                override fun onToggled(position: RoundingToggleSwitch.SwitchPosition) {
                    // do something...
                }
            }
        }
        
```


