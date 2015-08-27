LOCAL_PATH := $(call my-dir)
    
    include $(CLEAR_VARS)
    
    LOCAL_LDLIBS := -llog
    LOCAL_LDFLAGS += -ljnigraphics
    LOCAL_MODULE := NativeMethods
    LOCAL_SRC_FILES := native.c
    
    include $(BUILD_SHARED_LIBRARY)