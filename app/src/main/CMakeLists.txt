cmake_minimum_required(VERSION 3.6)

# 源文件
add_library(native-lib SHARED
            cpp/native_lib.cpp)

# 头文件路径
include_directories(cpp/object_class.h)

find_library( # Defines the name of the path variable that stores the
              # location of the NDK library.
              log-lib

              # Specifies the name of the NDK library that
              # CMake needs to locate.
              log)

# Include libraries needed for hello-jni lib
target_link_libraries(native-lib
                      ${log-lib}
                      android)