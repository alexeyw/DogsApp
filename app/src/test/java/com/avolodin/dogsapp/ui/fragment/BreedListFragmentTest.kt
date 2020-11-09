package com.avolodin.dogsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.avolodin.dogsapp.R
import com.avolodin.dogsapp.factory.ModelFactory
import com.avolodin.dogsapp.util.ViewModelUtil
import com.avolodin.dogsapp.presentation.BreedListViewModel
import com.avolodin.dogsapp.presentation.viewstate.BreedsUiState
import com.avolodin.dogsapp.util.RecyclerViewItemCountAssertion
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BreedListFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<TestBreedListFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer()
    }

    @Ignore("IllegalArgumentException: No injector factory bound")
    @Test
    fun breedListTest() {

        val breedsUiState = BreedsUiState.Success(ModelFactory.makeBreedViewList(5))
        fragmentScenario.onFragment {
            it.viewStateBreeds.postValue(breedsUiState)
        }

        Espresso.onView(ViewMatchers.withId(R.id.breed_list))
            .check(RecyclerViewItemCountAssertion(5))
    }

    class TestBreedListFragment : BreedListFragment() {
        private val viewModelFake = mock<BreedListViewModel>()
        val viewStateBreeds: MutableLiveData<BreedsUiState> =
            MutableLiveData<BreedsUiState>()

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            whenever(viewModelFake.viewStateBreeds).thenReturn(viewStateBreeds)
            this.viewModelFactory = ViewModelUtil.createFor(viewModelFake)
        }

    }


}