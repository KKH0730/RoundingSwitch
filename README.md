![rounding_switch](https://user-images.githubusercontent.com/66052075/204449881-1ae3d6d4-4fd4-4c96-a4d5-2480c7064a61.gif)



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
            indicatorColor = getColor(R.color.white)
            selectedTintColor = getColor(R.color.color_FF000000)
            unSelectedTintColor = getColor(R.color.color_FFADB5BD)
            setBackground(color = getColor(R.color.color_FF495057), backgroundRadius = 50f)
            sideMargin = 10f
            onSwitchToggleListener = object: RoundingToggleSwitch.OnSwitchToggleListener {
                override fun onToggled(position: RoundingToggleSwitch.SwitchPosition) {
                    // do something...
                }
            }
        }
        
```


