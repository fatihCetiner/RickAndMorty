package com.example.rickandmorty.ui.favorite

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentFavoriteFragmentsBinding
import com.example.rickandmorty.ui.favorite.adapter.FavoriteRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragments : Fragment() {

    private var _binding: FragmentFavoriteFragmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: FavoriteRecyclerAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    private val swipeCallBack =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            private var isSwiping = false

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedCharacter = mAdapter.currentList[layoutPosition]
                viewModel.deleteCharacter(selectedCharacter)
                Toast.makeText(requireContext(),"${selectedCharacter.name} "+getString(R.string.delete_character_msg),Toast.LENGTH_SHORT).show()

                val updatedList = mAdapter.currentList.toMutableList()
                updatedList.removeAt(layoutPosition)
                mAdapter.submitList(updatedList)

                checkListEmpty(updatedList.isEmpty())
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView

                if (dX < 0) {
                    val background = ColorDrawable(Color.RED)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    background.draw(c)

                    val deleteIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_delete) }
                    val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
                    val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0
                    val deleteIconMargin = (itemView.height - intrinsicHeight) / 2
                    val deleteIconTop = itemView.top + deleteIconMargin
                    val deleteIconBottom = deleteIconTop + intrinsicHeight
                    val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
                    val deleteIconRight = itemView.right - deleteIconMargin
                    deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                    deleteIcon?.draw(c)

                    // Kaydırma durumunu güncelleyin
                    isSwiping = dX != 0f
                } else {
                    isSwiping = false
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun onChildDrawOver(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (!isSwiping) {
                    super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteFragmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.page_favorites)

        setupRv()
        loadCharacters()
        observeCharacterList()
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.favCharacterRv)
    }

    private fun setupRv() {

        mAdapter = FavoriteRecyclerAdapter()
        binding.favCharacterRv.apply {

            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter

        }
    }

    private fun observeCharacterList() {
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            mAdapter.submitList(characters)
            checkListEmpty(characters.isEmpty())
        }
    }

    private fun loadCharacters() {
        viewModel.getAllCharacters()
    }

    private fun checkListEmpty(isEmpty: Boolean) {
        binding.isEmptyMessage.isVisible = isEmpty
        binding.favCharacterRv.isGone = isEmpty
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}