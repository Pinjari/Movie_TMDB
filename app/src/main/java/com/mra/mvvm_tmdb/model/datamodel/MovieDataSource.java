//package com.example.mvvm_tmdb.model.datamodel;
//
//import android.app.Application;
//import android.util.Log;
//
//
//import androidx.annotation.NonNull;
//import androidx.paging.PageKeyedDataSource;
//
//import com.example.mvvm_tmdb.model.response.Result;
//
//import java.util.ArrayList;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MovieDataSource extends PageKeyedDataSource<Long, Result> {
//    private MovieDataModel movieDataService;
//    private Application application;
//
//    public MovieDataSource(MovieDataModel movieDataService, Application application) {
//        this.movieDataService = movieDataService;
//        this.application = application;
//    }
//
//
//    @Override
//    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Result> callback) {
//
//
//        addDisposable(movieDataService.getMovieData(api_key, language, page, region)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ it ->
//                        it.run {
//                        this.results.forEach {result->
//                        Log.d("movieTitle", "$result.title")
//                        _movieResponseLiveData.postValue(this)
//                }
//                }
//                }, {
//                        Log.d(TAG, "response error, message : ${it.localizedMessage}")
//
//                }))
//
//        movieDataService = RetrofitInstance.getService();
//        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),1);
//
//        call.enqueue(new Callback<MovieDBResponse>() {
//            @Override
//            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
//
//                MovieDBResponse movieDBResponse=response.body();
//                ArrayList<Movie> movies = new ArrayList<>();
//
//                if(movieDBResponse != null && movieDBResponse.getMovies() != null) {
//                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
//
//                    callback.onResult(movies, null, (long) 2);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {
//
//    }
//
//    @Override
//    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Result> callback) {
//
//        movieDataService = RetrofitInstance.getService();
//        Call<MovieDBResponse> call = movieDataService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),params.key);
//
//        call.enqueue(new Callback<MovieDBResponse>() {
//            @Override
//            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
//
//                MovieDBResponse movieDBResponse=response.body();
//                ArrayList<Movie> movies = new ArrayList<>();
//
//                if(movieDBResponse != null && movieDBResponse.getMovies() != null) {
//                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
//
//                    callback.onResult(movies, params.key + 1);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//}
