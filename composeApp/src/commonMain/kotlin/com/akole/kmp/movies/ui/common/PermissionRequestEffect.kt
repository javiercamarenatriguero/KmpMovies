package com.akole.kmp.movies.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
fun PermissionRequestEffect(
    permission: Permission,
    onResult: (Boolean) -> Unit,
) {
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) { factory.createPermissionsController() }
    BindEffect(controller)

    // Execute every time controller changes
    LaunchedEffect(controller) {
        // Launch the permission request popup
        controller.providePermission(permission)
        onResult(controller.isPermissionGranted(permission))
    }
}