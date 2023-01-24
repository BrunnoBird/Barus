package com.bgaprojects.barus.collections

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bgaprojects.barus.R

class HabitListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val space = context.resources.getDimensionPixelSize(R.dimen.simple_margin)

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = 0
        outRect.right = 0
        outRect.top = space
        outRect.bottom = space
    }
}