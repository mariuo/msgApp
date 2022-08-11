import { AxiosRequestConfig } from 'axios';
import { useEffect, useState } from 'react';
import { SpringPage } from 'types/springPage';
import { User } from 'types/user';
import { requestBackEnd } from 'util/request';

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

        requestBackEnd(params).then((response) => {
            setPage(response.data);
        });
    }, []);

    return (
        <div>
            {page?.content.map((item) => (
                <p key={item.id}>{item.name}</p>
            ))}
        </div>
    );
};

export default Users;