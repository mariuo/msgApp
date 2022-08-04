import { useForm } from 'react-hook-form';
import { PostType } from 'types/postType';
import './styles.css';

type FormData = {
    content: string;
}
const PostCreate = () => {
    const { register, handleSubmit, } = useForm<FormData>();

    const onSubmit = (formData: FormData) => {
        console.log(formData);
    }
    return (
        <div className='create-post-container base-card'>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input
                    {...register('content')}
                    type="text"
                    placeholder="Type something..."
                    name="content"
                    className='form-control input-base mb-4'
                />
                <div className=''>
                    <button className='btn btn-primary button-post'>Post</button>
                </div>
            </form>
        </div>
    );
}
export default PostCreate;