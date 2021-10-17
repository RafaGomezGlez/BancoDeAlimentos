package tec.mx.bancodecomida


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.navArgs
import tec.mx.bancodecomida.Feed.InfoActivity
import tec.mx.bancodecomida.Feed.ui.DisplayNews
import tec.mx.bancodecomida.databinding.FragmentFeedBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentFeedBinding? = null
private val binding get() = _binding!!

//fragment_feed, is the id of the fragment, it's located in xml file
class FeedFragment : Fragment(R.layout.fragment_feed) {

    //Used to receive argument from the fragments LogIn SignIn
    private val args: FeedFragmentArgs by navArgs()

    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View {


        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentFeedBinding.inflate(inflater, container, false)

        return ComposeView(requireContext()).apply {
            setContent {
                DisplayNews {
                    startActivity(InfoActivity.intent(context,it))
                }
            }
        }

    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
