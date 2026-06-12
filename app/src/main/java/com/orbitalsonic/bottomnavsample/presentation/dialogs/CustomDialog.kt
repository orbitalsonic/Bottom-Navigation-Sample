package com.orbitalsonic.bottomnavsample.presentation.dialogs

import android.app.Activity
import com.orbitalsonic.bottomnavsample.helpers.ui.dialogHeight
import com.orbitalsonic.bottomnavsample.helpers.ui.dialogWidth
import com.orbitalsonic.bottomnavsample.helpers.ui.safeDialog
import com.orbitalsonic.bottomnavsample.presentation.dialogs.base.createAlertDialog

/*
fun Activity?.myCustomDialog(
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    safeDialog { activity ->

        val binding = DialogMyCustomBinding.inflate(activity.layoutInflater)

        val dialog = activity.createAlertDialog(
            binding = binding.root,
            cancelable = false,
            touchOutside = false
        )

        binding.dialogLayout.apply {
            layoutParams = layoutParams.apply {
                width = activity.dialogWidth(0.85)
                height = activity.dialogHeight(0.45)
            }
        }

        binding.btnCancel.setOnClickListener {
            onDenied()
            dialog.dismiss()
        }

        binding.btnDone.setOnClickListener {
            onGranted()
            dialog.dismiss()
        }

        dialog.show()
    }
}*/
