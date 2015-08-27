################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../jni/native.c 

OBJS += \
./jni/native.o 

C_DEPS += \
./jni/native.d 


# Each subdirectory must supply rules for building sources it contributes
jni/%.o: ../jni/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I/home/testuser/Desktop/MKR/MOVI_MAKER/android-ndk-r9b/platforms/android-9/arch-arm/usr/include -I/home/testuser/Desktop/MKR/MOVI_MAKER/android-ndk-r9b/platforms/android-9/arch-mips/usr/include -I/home/testuser/Desktop/MKR/MOVI_MAKER/android-ndk-r9b/platforms/android-9/arch-x86/usr/include -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


