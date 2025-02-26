package il.co.amikashkash.aquariumtoolkit

import android.app.Application
import il.co.amikashkash.aquariumtoolkit.elements.Graph

class AdditiveApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)

    }


    
}