################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/PAL4.cpp \
../src/working\ PAL4.cpp 

OBJS += \
./src/PAL4.o \
./src/working\ PAL4.o 

CPP_DEPS += \
./src/PAL4.d \
./src/working\ PAL4.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/working\ PAL4.o: ../src/working\ PAL4.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"src/working PAL4.d" -MT"src/working\ PAL4.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


