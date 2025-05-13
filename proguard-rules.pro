-dontwarn org.gradle.api.Plugin


-keepattributes SourceFile,LineNumberTable,Exceptions,Signature,*Annotation*

-keep public class * extends java.lang.Exception

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class androidx.lifecycle.** { *; }