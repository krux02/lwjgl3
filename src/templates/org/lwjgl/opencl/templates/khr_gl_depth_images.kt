/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.opencl.templates

import org.lwjgl.generator.*
import org.lwjgl.opencl.*

fun khr_gl_depth_images() = "KHRGLDepthImages".nativeClassCL("khr_gl_depth_images", KHR) {

	javaDoc("Native bindings to the <strong>$templateName</strong> extension.")

	IntConstant.block(
		"cl_channel_order",

		"DEPTH_STENCIL" _ 0x10BE
	)

	IntConstant.block(
		"cl_channel_type",

		"UNORM_INT24" _ 0x10DF
	)

}