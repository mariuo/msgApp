
import { AxiosRequestConfig } from 'axios';
import { useForm } from 'react-hook-form';
import { BASE_URL, requestBackend } from 'util/request';
import './styles.css';

type FormData = {
    content: string;
}
type Props = {
    userActiveId: number;
}
const PostCreateCard = ({ userActiveId }: Props) => {
    const { register, handleSubmit, formState: { errors }, } = useForm<FormData>();

    const onSubmit = (formData: FormData) => {
        const result = {
            content: formData.content,
            author: {
                id: userActiveId
            }
        }
        const params: AxiosRequestConfig = {
            method: 'POST',
            url: "/post",
            baseURL: BASE_URL,
            withCredentials: true,
            data: result
        }

        requestBackend(params)
            .then(response => {
                window.location.reload();
            })

    }
    return (
        <div className='create-post-container base-card'>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input
                    {...register('content', {
                        required: 'Required field.'
                    })}
                    type="text"
                    placeholder="Type something..."
                    name="content"
                    className={`input-base form-control ${errors.content ? 'is-invalid' : ''}`}

                />
                <div className='invalid-feedback d-block'>{errors.content?.message}</div>
                <div className=''>
                    <button className='btn btn-primary button-post mt-2'>Post</button>
                </div>
            </form>
        </div>
    );
}
export default PostCreateCard;