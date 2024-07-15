package com.marvel.data.usecases

import com.marvel.core.service.repositories.MainRepository
import com.marvel.core.utils.BaseResponse
import com.marvel.data.responsemodels.CharactersResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(offset:Int): Flow<BaseResponse<List<CharactersResponseModel>>> = flow {
        try {
            emit(BaseResponse.Loading<List<CharactersResponseModel>>())
            val list = mainRepository.getAllCharacters(offset=offset).data.results.map {
                it.toCharacterResponseModel()
            }
            emit(BaseResponse.Success<List<CharactersResponseModel>>(list))
        }
        catch (e:HttpException){
            emit(BaseResponse.Error<List<CharactersResponseModel>>(e.printStackTrace().toString()))
        }
        catch (e:IOException){
            emit(BaseResponse.Error<List<CharactersResponseModel>>(e.printStackTrace().toString()))
        }
    }
}