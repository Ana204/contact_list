# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#######################
# MOSHI
#######################

# JSR 305 annotations are for embedding nullability information.
#-dontwarn javax.annotation.** - comentei/descomentar dps

#-keepclasseswithmembers class * {
    #@com.squareup.moshi.* <methods>;
#} - comentei/descomentar dps

#-keep @com.squareup.moshi.JsonQualifier interface * - comentei/descomentar dps

# Enum field names are used by the integrated EnumJsonAdapter.
# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.
#-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    #<fields>;
#}- comentei/descomentar dps

# The name of @JsonClass types is used to look up the generated adapter.
#-keepnames @com.squareup.moshi.JsonClass class * - comentei/descomentar dps

# Retain generated JsonAdapters if annotated type is retained.
#-if @com.squareup.moshi.JsonClass class *  - comentei/descomentar dps
#-keep class <1>JsonAdapter {
    #<init>(...);
    #<fields>;
#} - comentei/descomentar dps
#-if @com.squareup.moshi.JsonClass class **$* - comentei/descomentar dps
#-keep class <1>_<2>JsonAdapter {
   # <init>(...);
   # <fields>;
#}  - comentei/descomentar dps
#-if @com.squareup.moshi.JsonClass class **$*$* - comentei/descomentar dps
#-keep class <1>_<2>_<3>JsonAdapter {
  #  <init>(...);
   # <fields>;
#}- comentei/descomentar dps
#-if @com.squareup.moshi.JsonClass class **$*$*$*- comentei/descomentar dps
#-keep class <1>_<2>_<3>_<4>JsonAdapter {
    #<init>(...);
    #<fields>;
#}- comentei/descomentar dps
#-if @com.squareup.moshi.JsonClass class **$*$*$*$* - comentei/descomentar dps
#-keep class <1>_<2>_<3>_<4>_<5>JsonAdapter {
    #<init>(...);
    #<fields>;
#}- comentei/descomentar dps
#-if @com.squareup.moshi.JsonClass class **$*$*$*$*$*- comentei/descomentar dps
#-keep class <1>_<2>_<3>_<4>_<5>_<6>JsonAdapter {
   # <init>(...);
   # <fields>;
#}- comentei/descomentar dps

#######################
# MOSHI KOTLIN
#######################

#-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl - comentei/descomentar dps

#-keepclassmembers class kotlin.Metadata { - comentei/descomentar dps
  #  public <methods>;
#}- comentei/descomentar dps

#######################
# OKHTTP
#######################

# JSR 305 annotations are for embedding nullability information.
#-dontwarn javax.annotation.** - comentei/descomentar dps - comentei/descomentar dps

# A resource is loaded with a relative path so the package of this class must be preserved.
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase - comentei/descomentar dps

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
#-dontwarn org.codehaus.mojo.animal_sniffer.* - comentei/descomentar dps

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
#-dontwarn okhttp3.internal.platform.ConscryptPlatform - comentei/descomentar dps

#######################
# RETROFIT
#######################

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement - comentei/ descomentar dps

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>