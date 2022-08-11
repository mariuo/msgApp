import { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { SpringPage } from 'types/springPage';
import { User } from 'types/user';
import { requestBackend } from 'util/request';
import './styles.css';

const Users = () => {
    const [page, setPage] = useState<SpringPage<User>>();

    useEffect(() => {
        const params: AxiosRequestConfig = {
            url: '/user',
            withCredentials: true,
            params: {
                page: 0,
                size: 12,
            },
        };

        requestBackend(params).then((response) => {
            setPage(response.data);
        });
    }, []);

    return (
        <div>
            {page?.content.map((item) => (
                <div className="user-info" key={item.id}>

                    <img src={item?.imageUrlProfile} alt={item?.name} />
                    <h6>ID#<b> {item.id} </b> : {item.name}</h6>


                </div>

            ))}
        </div>
    );
};

export default Users;