import testplugin.PluginSettings
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.PowerManager
import android.widget.ImageView
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.PreHook
import com.aliucord.patcher.after
import com.aliucord.patcher.before
import com.aliucord.patcher.component1
import com.aliucord.patcher.component2
import com.aliucord.utils.DimenUtils
import com.discord.models.presence.Presence
import com.discord.utilities.icon.IconUtils
import com.discord.utilities.images.MGImages
import com.discord.utilities.presence.PresenceUtils
import com.discord.utilities.view.ToastManager
import com.facebook.drawee.view.SimpleDraweeView

@AliucordPlugin
class TestPlugin : Plugin() {
    init {
        settingsTab = SettingsTab(PluginSettings::class.java, SettingsTab.Type.BOTTOM_SHEET)
            .withArgs(settings)
    }

    override fun start(context: Context) {}
    override fun stop(context: Context) {}
}
