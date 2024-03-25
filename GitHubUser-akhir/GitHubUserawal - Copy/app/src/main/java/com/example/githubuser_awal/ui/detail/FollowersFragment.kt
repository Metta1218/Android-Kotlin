import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_awal.R
import com.example.githubuser_awal.databinding.FragmentFollowBinding
import com.example.githubuser_awal.ui.detail.DetailUserActivity
import com.example.githubuser_awal.ui.detail.FollowersViewModel
import com.example.githubuser_awal.ui.main.UserAdapter

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = UserAdapter()

        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(activity)
            rvUsers.adapter = adapter
        }
        showLoading(true)

        viewModel = ViewModelProvider(this)[FollowersViewModel::class.java]
        viewModel.setListFollowers(requireContext(), username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) { userList ->
            userList?.let {
                adapter.setList(it)
                showLoading(false)
            } ?: Toast.makeText(activity, "Failure", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}
