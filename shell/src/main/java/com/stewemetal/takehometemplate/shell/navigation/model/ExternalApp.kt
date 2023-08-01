package com.stewemetal.takehometemplate.shell.navigation.model

import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Representation of an external application that can be opened with the specified [intent].
 */
sealed class ExternalApp(
    internal val intent: Intent,
)

data class ShareSheet(
    val type: String,
    val contentUri: Uri? = null,
    val subject: String? = null,
    val text: String? = null,
) : ExternalApp(
    intent = Intent(Intent.ACTION_SEND).apply {
        setType(type)
        if (subject.isNullOrEmpty().not()) {
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (text.isNullOrEmpty().not()) {
            putExtra(Intent.EXTRA_TEXT, text)
        }
        contentUri?.let { uri ->
            putExtra(Intent.EXTRA_STREAM, uri)
        }
    },
)

data class Browser(
    val uri: Uri,
) : ExternalApp(
    intent = Intent(Intent.ACTION_VIEW).setData(uri),
)

data class Dialer(
    val phoneNumber: String? = null,
) : ExternalApp(
    intent = if (phoneNumber.isNullOrEmpty()) {
        Intent(Intent.ACTION_DIAL)
    } else {
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    },
)

data class GoogleMaps(
    val addressUri: Uri,
) : ExternalApp(
    intent = Intent(Intent.ACTION_VIEW, addressUri).setPackage("com.google.android.apps.maps"),
)

/**
 * Open the default Navigation App to navigate to a geographical point defined by [longitude] and [latitude].
 */
data class AddressNavigation(
    val latitude: Double,
    val longitude: Double,
    val zoom: Int? = null,
) : ExternalApp(
    intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:$latitude,$longitude?${if (zoom != null) "z=$zoom" else ""}"),
    ),
)

/**
 * Open the settings page for an application defined by its [packageName].
 */
data class DeviceSettings(
    val packageName: String,
) : ExternalApp(
    intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", packageName, null)
    },
)
