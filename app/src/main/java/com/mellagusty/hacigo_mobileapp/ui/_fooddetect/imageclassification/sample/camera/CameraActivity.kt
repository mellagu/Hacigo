/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.tflite.imageclassification.sample.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mellagusty.hacigo_mobileapp.R
import com.mellagusty.hacigo_mobileapp.data.auth.emailauth.UserEmail
import com.mellagusty.hacigo_mobileapp.ui._fooddetect.imageclassification.sample.camera.Communicator
import com.mellagusty.hacigo_mobileapp.ui.recipes.RecipesFragment

class CameraActivity : AppCompatActivity(),Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        savedInstanceState ?: supportFragmentManager.beginTransaction()
            .replace(R.id.container, Camera2BasicFragment.newInstance())
            .commit()
    }

    override fun passDataCom(textInput: String) {
        val bundle = Bundle()
        bundle.putString("bahan",textInput)

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag = RecipesFragment()
        frag.arguments = bundle

        transaction.replace(R.id.container, frag)
        transaction.commit()
    }


}


