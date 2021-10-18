package tec.mx.bancodecomida.Settings

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tec.mx.bancodecomida.R
import kotlinx.android.synthetic.main.fragment_settings_support_agreements.view.*
import tec.mx.bancodecomida.MainActivity
import tec.mx.bancodecomida.databinding.FragmentSettingsSupportAgreementsBinding
import tec.mx.bancodecomida.databinding.FragmentSettingsSupportBinding
import tec.mx.bancodecomida.settingsFragmentDirections


private  const val LINK ="link"

private var _binding: FragmentSettingsSupportAgreementsBinding? = null
private val binding get() = _binding!!

class settings_support_agreements : Fragment() {
    private lateinit var pageUrl : String
    private lateinit var webView : WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageUrl = it.getString(LINK).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_support_agreements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.isFocusableInTouchMode = true
        val requestFocus = view.requestFocus()

        webView = view.fragwebView
        progressBar = view.fragprogressBar
        swipeRefreshLayout = view.fragswipe
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener { webView.reload() }

        setWebClient()
        initWebView()
        loadUrl(pageUrl)

        val onBackPressed  = object : BroadcastReceiver()
        {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(webView.canGoBack())
                    webView.goBack()
                else
                    //requireActivity().onBackPressed()
                    (activity as MainActivity?)?.rmAgreement()
            }

        }
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(onBackPressed,
            IntentFilter(this.hashCode().toString())
        )
    }

    private fun setWebClient() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress < 100 && progressBar.visibility == ProgressBar.GONE) {
                    progressBar.visibility = ProgressBar.VISIBLE
                }

                if (newProgress == 100) {
                    progressBar.visibility = ProgressBar.GONE
                    swipeRefreshLayout.isRefreshing=false
                }
            }
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.settings.setAppCacheEnabled(true)
        webView.settings.databaseEnabled = true
        webView.settings.setAppCachePath(requireContext().applicationContext.filesDir.absolutePath+"/cache")

        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
        webView.run {
            webView.viewTreeObserver.addOnScrollChangedListener {
                swipeRefreshLayout.isEnabled = webView.scrollY == 0
            }
        }
    }
    private fun loadUrl(pageUrl: String) {
        webView.loadUrl(pageUrl)
    }
    companion object {
        @JvmStatic
        fun newInstance(url : String) =
            settings_support_agreements().apply {
                arguments = Bundle().apply {
                    putString(LINK, url)
                }
            }
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}